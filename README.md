# learn-gatling-example

This repository contains a Gatling performance testing project. It is set up to run Gatling simulations from a project-specific directory, keeping the Gatling installation separate.

## Prerequisites

* **Java:** OpenJDK 11 or later.
* **Gatling:** Downloaded and installed separately (not included in this repository).

## Installation and Setup

1.  **Install Java:**

    ```bash
    sudo apt update
    sudo apt install openjdk-11-jdk
    ```

2.  **Download and Install Gatling (Separately):**

    * Download the Gatling bundle from the official Gatling website.
    * Extract the bundle to a location of your choice (e.g., `/opt/gatling/` or `~/gatling/`).

3.  **Clone this Repository:**

    ```bash
    git clone [https://github.com/Muyundo/learn-gatling-example.git](https://github.com/Muyundo/learn-gatling-example.git)
    cd learn-gatling-example
    ```

## Project Structure

learn-gatling-example/
├── gatling-simulations/
│   └── SimpleSimulation.scala
├── gatling-reports/
├── run-gatling.sh
└── .gitignore


* `gatling-simulations/`: Contains the Gatling simulation files (`*.scala`).
* `gatling-reports/`: Will contain the generated Gatling reports.
* `run-gatling.sh`: Script to run Gatling.
* `.gitignore`: Specifies files to ignore from version control.

## Running the Simulation

1.  **Navigate to the Project Directory:**

    ```bash
    cd learn-gatling-example
    ```

2.  **Make the `run-gatling.sh` script executable:**

    ```bash
    chmod +x run-gatling.sh
    ```

3.  **Run the Simulation:**

    ```bash
    ./run-gatling.sh
    ```

    * **Note:** Ensure that the path to your Gatling installation in `run-gatling.sh` is correct. If you installed gatling in your home directory, it should be `/home/muyundo/gatling/bin/gatling.sh`. If you installed it in the opt directory, it should be `/opt/gatling/bin/gatling.sh`.

4.  **View the Reports:**

    * The reports will be generated in the `gatling-reports/` directory. Open `index.html` in your web browser.

## Gatling Simulation (SimpleSimulation.scala)

```scala
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SimpleSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("[http://httpbin.org](http://httpbin.org)")
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
.gitignore
gatling-reports/
/opt/gatling/ # Or /home/muyundo/gatling/ if in your home directory.
target/
.metals/
Important Notes
This project assumes Gatling is installed separately.
Adjust the Gatling installation path in run-gatling.sh to match your setup.
The .gitignore file prevents the Gatling installation and reports from being pushed to GitHub.