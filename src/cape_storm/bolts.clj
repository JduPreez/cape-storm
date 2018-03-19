(ns cape-storm.bolts
  (import   com.mashape.unirest.http.Unirest)
  (:use     [cape-storm.authentication])
  (:require [backtype.storm.log :as log])
  (:require [backtype.storm [clojure :refer [emit-bolt! defbolt ack! bolt]]]))

(defbolt stormy-bolt ["stormy"] [{type :type :as tuple} collector]
  (emit-bolt! collector [(case type
                           :regular `"I'm regular Stormy!"
                           :bizarro "I'm bizarro Stormy!"
                           "I have no idea what I'm doing.")]
              :anchor tuple)
  (ack! collector tuple))

(def data
  (let [tkn   (token "https://auth-server/connect/token" "token-name" "d30g193f-7d5b-5bc5-8386-794caebfbb4d")
        req   (Unirest/get "http://localhost:8550/api/companies")
        resp  (.asString (tokenize req tkn))]
    (.getBody resp)))

(defbolt cape-storm-bolt ["message"] [{stormy :stormy :as tuple} collector]
  (log/log-message "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&")
  (log/log-message data)

  (emit-bolt! collector [(str "cape-storm produced: " stormy)] :anchor tuple)
  (ack! collector tuple))
