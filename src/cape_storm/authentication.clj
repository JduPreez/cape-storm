(ns cape-storm.authentication
  (import com.mashape.unirest.http.Unirest))

(defn token [auth-url key secret]
  (let [resp    (.asJson 
                  (.basicAuth 
                    (.field (Unirest/post auth-url) "grant_type" "client_credentials") key secret))
        tokn    (.getString (.getObject (.getBody resp)) "access_token")]
        tokn))

(defn tokenize [request token]
    (.header request "Authorization", (str "Bearer " token)))