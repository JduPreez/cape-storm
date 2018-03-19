(ns cape-storm.topology
  (:require [cape-storm
             [spouts :refer [type-spout]]
             [bolts :refer [stormy-bolt cape-storm-bolt]]]
            [backtype.storm [clojure :refer [topology spout-spec bolt-spec]] [config :refer :all]])
  (:import [backtype.storm LocalCluster LocalDRPC]))

(defn stormy-topology []
  (topology
   {"spout" (spout-spec type-spout)}
   {"stormy-bolt" (bolt-spec {"spout" ["type"]} stormy-bolt :p 2)
    "cape-storm-bolt" (bolt-spec {"stormy-bolt" :shuffle} cape-storm-bolt :p 2)}))

(defn run! [& {debug "debug" workers "workers" :or {debug "true" workers "2"}}]
  (doto (LocalCluster.)
    (.submitTopology "stormy topology"
                     {TOPOLOGY-DEBUG (Boolean/parseBoolean debug)
                      TOPOLOGY-WORKERS (Integer/parseInt workers)}
                     (stormy-topology))))
