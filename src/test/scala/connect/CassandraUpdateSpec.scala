package connect

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._
import org.scalatest.{Matchers, FunSpec}
import util.{Helper, CassandraConnectionUri}

class CassandraUpdateSpec extends FunSpec with Matchers {

  describe("Update data on cassandra") {
    it("should update first in emp with id = 1") {
      val uri = CassandraConnectionUri("cassandra://localhost:9042/dev")
      val session = Helper.createSessionAndInitKeyspace(uri)

      val countResult = session.execute("select max(empid) as maxemp from emp")
      val highNumber = countResult.one().getInt("maxemp")

      val updateStatement = update("emp")
          .`with`(set("emp_first","test"))
          .where(QueryBuilder.eq("empid",highNumber))

      val updateResult = session.execute(updateStatement)

      updateResult.wasApplied() should be (true)
    }
  }
}
