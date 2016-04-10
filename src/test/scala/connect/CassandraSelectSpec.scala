package connect

import com.datastax.driver.core.querybuilder.QueryBuilder
import org.scalatest.{Matchers, FunSpec}
import util.{CassandraConnectionUri, Helper}
import com.datastax.driver.core.querybuilder.QueryBuilder._

class CassandraSelectSpec extends FunSpec with Matchers {

  describe("A select statement on cassandra") {
    it("should get emp_last that is last where id is largest") {
      val uri = CassandraConnectionUri("cassandra://localhost:9042/dev")
      val session = Helper.createSessionAndInitKeyspace(uri)

      val countResult = session.execute("select max(empid) as maxemp from emp")
      val highNumber = countResult.one().getInt("maxemp")

      val statement = select().column("emp_last").from("emp")
          .where(QueryBuilder.eq("empid",highNumber))

      val result = session.execute(statement).one()

      result.getString("emp_last") should be ("last")
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
