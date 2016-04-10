package connect

import com.datastax.driver.core.querybuilder.QueryBuilder._
import org.scalatest.{Matchers, FunSpec}
import util.{Helper, CassandraConnectionUri}

class CassandraInsertSpec extends FunSpec with Matchers {

  describe("Data insertion on a cassandra") {
    it("should insert a emp record") {
      val uri = CassandraConnectionUri("cassandra://localhost:9042/dev")
      val session = Helper.createSessionAndInitKeyspace(uri)

      val countStatement = select().from("emp")
      val countResult = session.execute(countStatement)

      val statement = insertInto("emp").value("empid",countResult.all().size()+1)
        .value("emp_first","first").value("emp_last","last")
        .value("emp_dept","dept")

      val result = session.execute(statement)

      result.wasApplied() should be (true)
    }
  }
}
