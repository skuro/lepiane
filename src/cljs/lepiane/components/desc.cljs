(ns lepiane.components.desc
  (:require [lepiane.data.lang :as lang]))

(defn size
  [{:keys [size]}]
  [:li "∅ " size "m" [:sup "2"]])

(defn toilets
  [{:keys [toilets]}]
  [:li "⚤ " toilets " " (lang/string [:sections :house-details :toilets])])

(defn pax
  [{:keys [pax]}]
  [:li "☻ up to n. " (:adults pax) " guests"
   (if-let [children (:children pax)]
     [:span " (plus n. " children " small children)"])])

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
