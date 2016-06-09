(ns watch-politics-clojure.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.data.xml :as xml]))

(defn extract-json [response-body]
  (json/read-str response-body))

(defn make-call [url]
  (:body
    (client/get url)))

(defn get-it [url]
  (println (extract-json
             (make-call url))))

(defn http-call [url]
  (println (client/get url)))

(defn -main []  
  (get-it "http://ip.jsontest.com/")
  (http-call "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoesVotadasEmPlenario?ano=2013&tipo="))

(-main)
