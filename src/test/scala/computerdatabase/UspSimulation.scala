package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class USPSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://vod-hls-uk-bidi.mf.stage.bbc.co.uk") 
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")


  val scn = scenario("Playlist item")
    .exec(http("request 1")
      .get("/usp/auth/vod/piff_abr_full_hd/b0084d73/vf_b0084d73_6ec27b29-ffdd-416b-9ef8-05fee2190af4.ism/cellular_baseline_sd_abr_hds_v1_master/vf_b0084d73_6ec27b29-ffdd-416b-9ef8-05fee2190af4-pv10=1604000-161.ts")
      .check(status.is(200)))

  setUp(scn.inject(
     constantUsersPerSec(2) during(30)    
  ).protocols(httpConf))
}
