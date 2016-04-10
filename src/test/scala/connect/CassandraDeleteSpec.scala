package connect

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._
import org.scalatest.{Matchers, FunSpec}
import util.{Helper, CassandraConnectionUri}

class CassandraDeleteSpec extends FunSpec with Matchers {

  describe("Data deletion on cassandra") {
    it("should delete record with id equal to the size") {
      val uri = CassandraConnectionUri("cassandra://localhost:9042/dev")
      val session = Helper.createSessionAndInitKeyspace(uri)

      val countResult = session.execute("select max(empid) as maxemp from emp")
      val highNumber = countResult.one().getInt("maxemp")

      val deleteStatement = delete().from("emp")
          .where(QueryBuilder.eq("empid",highNumber))
      val deleteResult = session.execute(deleteStatement)

      deleteResult.wasApplied() should be (true)
    }
  }
}
