package connect

import com.datastax.driver.core.querybuilder.QueryBuilder
import org.scalatest.{Matchers, FunSpec}
import util.{CassandraConnectionUri, Helper}
import com.datastax.driver.core.querybuilder.QueryBuilder._

class CassandraSelectSpec extends FunSpec with Matchers {

  describe("A select statement on cassandra") {
    it("should get emp_first that is fred where id is 1") {
      val uri = CassandraConnectionUri("cassandra://localhost:9042/dev")
      val session = Helper.createSessionAndInitKeyspace(uri)

      val statement = select().column("emp_first").from("emp")
          .where(QueryBuilder.eq("empid",1))
            .limit(1)

      val result = session.execute(statement).one()

      result.getString("emp_first") should be ("fred")
    }

    it("should get list with rows containing all emp") {
      val uri = CassandraConnectionUri("cassandra://localhost:9042/dev")
      val session = Helper.createSessionAndInitKeyspace(uri)

      val statement = select().all().from("emp")

      val result = session.execute(statement).all()

      result.size() should be > 0
    }
  }
}
