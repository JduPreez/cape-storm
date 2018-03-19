(defproject cape-storm "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [ [org.clojure/clojure "1.4.0"]
                  [com.mashape.unirest/unirest-java "1.4.9"]
                  ;[org.apache.httpcomponents/httpclient "4.3.6"]
                  ;[org.apache.httpcomponents/httpasyncclient "4.0.2"]
                  ;[org.apache.httpcomponents/httpmime "4.3.6"] 
                  [org.json/json "20140107"] ]
  :aot [cape-storm.TopologySubmitter]
  ;; include storm dependency only in dev because production storm cluster provides it
  :profiles {:dev {:dependencies [[storm "0.8.1"]]}})
