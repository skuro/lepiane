(ns lepiane.components.desc
  (:require [lepiane.data.lang :as lang]))

(defn list-char
  [char]
  [:span {:style {:font-size "2em"}}
   char " "])

(defn size
  [{:keys [size]}]
  [:li [list-char "∅"] size "m" [:sup "2"]])

(defn toilets
  [{:keys [toilets]}]
  [:li [list-char "⚤"] toilets " " (lang/string [:sections :house-details :toilets])])

(defn pax
  [{:keys [pax]}]
  [:li [list-char "☺"] (lang/string [:sections :house-details :pax :start]) " " (:adults pax) " " (lang/string [:sections :house-details :pax :end])
   (if-let [children (:children pax)]
     [:span " " (lang/string [:sections :house-details :pax :children-start]) children " " (lang/string [:sections :house-details :pax :children-end])])])

(defn description [desc]
  [:div.container-fluid
   [:div.row
    [:div.col-xs-11 {:style {:margin-left "-30px"}}
     [:h4 (lang/string [:sections :house-details :title])]
     [:ul {:style {:list-style "none"}}
      [size desc]
      [pax desc]
      [toilets desc]]
     [:p desc]]]])
