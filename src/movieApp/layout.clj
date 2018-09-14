(ns movieApp.layout
  (:use [hiccup.element :only (link-to)])
  (:require [hiccup.page :as h]
            [hiccup.page :refer [html5 include-css]]))

(defn common-layout [header & body]
  (h/html5
    [:head
     [:title "Movie"]
     (include-css "/css/bootstrap.css")
     ]
    [:body
     [:div {:class "header"}
      [:h1 {:class "container"} header]]
     [:div {:id "content" :class "container"} body]]))

(defn not-found []
  (common-layout "NOT FOUND" [:div {:id "error"} "Unfortunately, the page you requested could not be found!!!"]))