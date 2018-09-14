(ns movieApp.view
  (:use hiccup.page hiccup.element)
  (:require 
    [hiccup.core :refer [h]]
    [hiccup.form :as form]
    [clojure.string :as str]
    [movieApp.layout :as layout]
    [ring.util.anti-forgery :as anti-forgery]))


(defn display-all-movies [movies]
  [:div {:class "card text-center"}
   [:h1 "All movies"]
   [:p "*Here are the best movies in last 20 years!*"]
   [:br]
   [:table  {:class "table table-bordered"}
    [:th "Id"]
    [:th "Name"]
    [:th "Year"]
    [:th "Description"]
    [:th "Genre"]
    [:th "Remove"]
    [:th "Edit"]
    (map 
      (fn [movie]
        [:tr 
         [:td (h (:id movie))]
         [:td (h (:name movie))]
         [:td (h (:year movie))]
         [:td (h (:description movie))]
         [:td (h (:genre movie))]
         [:td [:a {:href (str "/delete/" (h (:id movie)))} "Remove"]]
         [:td [:a {:href (str "/update/" (h (:id movie)))} "Edit"]]
         ]) movies)]])

(defn add-movie-form []
  [:div {:class "form-group card"} 
   [:h1 {:class "text-center"} "Add movie"]
   (form/form-to [:post "/"]
                 (anti-forgery/anti-forgery-field)
                 [:div {:class "form-group"}
                  (form/label "name" "Name")
                  (form/text-field {:class "form-control"} "name")]
                 [:div {:class "form-group"}
                  (form/label "year" "Year")
                  (form/text-field {:class "form-control"} "year")]
                 [:div {:class "form-group"}                  
                  (form/label "description" "Description ")                  
                  (form/text-field {:class "form-control"}  "description")]
                 [:div {:class "form-group"}                  
                  (form/label "Genre" "genre")
                  (form/text-field {:class "form-control"}  "genre")]
                 (form/submit-button {:class "btn btn-primary btn-lg btn-block"}  "Add movie")
                 [:br])])

(defn index-page [movies]
  (layout/common-layout ""
                        [:div {:class "col-lg-1"}]
                        [:div {:class "col-lg-10"}
                         (display-all-movies movies)
                         (add-movie-form)]
                        [:div {:class "col-lg-1"}]   
                        ))


(defn update-movie-form [movie]
   (layout/common-layout "" 
  [:div {:class "form-group card"} 
   [:h1 {:class "text-center"} "Edit movie"]
    (map 
      (fn [movie]
     (form/form-to [:post "/update-movie"]
                 (anti-forgery/anti-forgery-field)
                 (form/hidden-field "id" (:id movie))
                 [:div {:class "form-group"}
                  (form/label "name" "Name")
                  (form/text-field {:class "form-control"} "name" (:name movie))]
                   [:div {:class "form-group"}
                    (form/label "year" "year")
                    (form/text-field {:class "form-control"} "year" (:year movie))]
                 [:div {:class "form-group"}
                  (form/label "description" "Description ")                  
                  (form/text-field {:class "form-control"}  "description" (:description movie))]
                 [:div {:class "form-group"}                  
                  (form/label "genre" "Genre")
                  (form/text-field {:class "form-control"}  "genre" (:genre movie))]
                 (form/submit-button {:class "btn btn-primary btn-lg btn-block"}  "Edit movie")
                 [:br])) movie)]
    
))


