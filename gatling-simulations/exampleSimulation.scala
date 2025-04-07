import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SimpleSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://httpbin.org")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")

  val scn = scenario("SimpleGetRequest")
    .exec(http("Get Root")
      .get("/"))

  setUp(scn.inject(
    constantUsersPerSec(10).during(10.seconds)
  ).protocols(httpProtocol))
}