package connect

import org.scalatest.{FunSpec, Matchers}
import util.CassandraConnectionUri

class CassandraUriSpec extends FunSpec with Matchers {

  describe("A cassandra URI connection URI object") {
    it("should parse a URI with a single host") {
      val cut = CassandraConnectionUri("cassandra://localhost:9042/dev")
      cut.host should be ("localhost")
      cut.port should be (9042)
      cut.keyspace should be ("dev")
    }
  }
}
