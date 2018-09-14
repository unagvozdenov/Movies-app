(ns movieApp.handler
    (:use ring.middleware.json 
        ring.adapter.jetty)
  (:require [compojure.core :refer [defroutes ANY]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [movieApp.layout :as layout]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [movieApp.core :as core]
            ))

(defroutes routes
  core/my_routes
  (route/resources "/")
  )


(def app
  (wrap-defaults routes site-defaults))
