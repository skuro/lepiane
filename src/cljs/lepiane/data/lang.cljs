(ns lepiane.data.lang
  (:require [reagent.ratom :refer [atom]]))

(def default-lang "en")

(def langs
  {"en" :en
   "it" :it
   "de" :de
   "fr" :fr
   "nl" :nl})

(defn script->lang
  [script]
  (if script
    (get langs script "en")
    default-lang))

(defn detect-language
  []
  (let [lang-script (.getElementById js/document "__lang-detect")]
    (script->lang lang-script)))

(def current (atom (detect-language)))

(defn switch-language
  [lang]
  (reset! current (langs lang :en)))

(defn event->language [ev]
  (let [select-box (.. ev -target)
        selected (aget (.-selectedOptions select-box) 0)]
    (when selected
      (.log js/console "storing" (.. selected -id))
      (reset! current (.. selected -id)))))

(defn lang-switcher [selected-lang]
  [:select {:id "lang"
            :on-change event->language}
   (for [lang (keys langs)]
     [:option {:id lang
               :key (gensym)} lang])])
