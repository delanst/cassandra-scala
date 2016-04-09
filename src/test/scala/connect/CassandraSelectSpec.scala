package connect

import com.datastax.driver.core.querybuilder.QueryBuilder
import org.scalatest.{Matchers, FunSpec}
import util.{CassandraConnectionUri, Helper}
import com.datastax.driver.core.querybuilder.QueryBuilder._

class CassandraSelectSpec extends FunSpec with Matchers {

  describe("A select statement on cassandra") {
    it("should get information from emp") {
      val uri = CassandraConnectionUri("cassandra://localhost:9042/dev")
      val session = Helper.createSessionAndInitKeyspace(uri)

      val statement = select().column("emp_first").from("emp")
          .where(QueryBuilder.eq("empid",1))
            .limit(1)

      val result = session.execute(statement).one()

      result.getString("emp_first") should be ("fred")
    }
  }
}
