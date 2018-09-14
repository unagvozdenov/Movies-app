(ns movieApp.db
  (:require [clojure.java.jdbc :as sql]))

(def connection 
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :subname "//localhost:3306/movies?autoReconnect=true&useSSL=false"

   :user "root"
   :password ""})

(defn create-movie [name year description genre]
  (sql/insert! connection :movie [:name :year :description :genre] [name year description genre]))

(defn delete-movie [id]
 (sql/delete! connection :movie
            ["id = ?" id]))

(defn get-all-movies []
  (into [] (sql/query connection ["select * from movie"])))

(defn get-movie-by-id [id]
    (into [] (sql/query connection ["select * from movie where id = ?" id])))

(defn update-movie [id name year description genre]

  (sql/update! connection :movie {:id id :name name :year year :description description :genre genre } ["id = ?" id]))
