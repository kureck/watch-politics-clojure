(ns watch-politics-clojure.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(defn extract-json [response-body]
  (get (json/read-str response-body) "value 1"))

(defn make-call [url input]
  (:body
    (client/get url
                {:query-params {"value1" input}})))

(defn get-it [url input]
  (println (extract-json
             (make-call url input))))

(defn http-call [url]
  (println (client/get url)))

(defn -main []  (get-it "http://ip.jsontest.com/"
                        "I am become"))

(-main)
