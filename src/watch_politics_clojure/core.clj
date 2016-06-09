(ns watch-politics-clojure.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.data.xml :as xml]
            [clojure.zip :as zip]))

(defn extract-json [response-body]
  (json/read-str response-body))

(defn make-call [url]
  (:body
    (client/get url)))

(defn get-it [url]
  (println (extract-json
             (make-call url))))

(defn http-call [url]
  (client/get url))

(defn parse [s]
  (xml/parse
   (java.io.ByteArrayInputStream. (.getBytes s))))

(defn xml-zip [xml]
  (zip/xml-zip
    (parse xml)))

(defn xml-reader [input]
  (parse (:body input)))

(defn -main []  
  ;(get-it "http://ip.jsontest.com/")
  (xml-reader 
    (http-call "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoesVotadasEmPlenario?ano=2013&tipo=")))

(-main)
