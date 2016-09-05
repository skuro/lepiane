(ns lepiane.data.lang
  (:require [reagent.ratom :refer [atom]]

            [lepiane.data.lang.en :as en]
            [lepiane.data.lang.it :as it]))

(def default-lang "en")

(def langs
  {"en" :en
   "it" :it
   "de" :de
   "fr" :fr
   "nl" :nl})

(def strings
  {:en en/strings
   :it it/strings})

(defn script->lang
  [script]
  (if script
    (get langs script "en")
    default-lang))

(defn detect-language
  []
  (let [lang-script (.getElementById js/document "__lang-detect")]
    (script->lang lang-script)))

(defn lang-map [lang]
  {:lang lang
   :strings (or (strings lang)
                (strings :en))})

(def current (atom (lang-map (detect-language))))

(defn switch-language
  [lang]
  (when-let [l (langs lang :en)]
    (reset! current (lang-map l))))

(defn event->language [ev]
  (let [select-box (.. ev -target)
        selected (aget (.-selectedOptions select-box) 0)]
    (when selected
      (switch-language (.. selected -id)))))

(defn lang-switcher [selected-lang]
  [:select {:id "lang"
            :on-change event->language}
   (for [lang (keys langs)]
     [:option {:id lang
               :key (gensym)} lang])])

(defn string [path]
  (let [strings (:strings @current)]
    (get-in strings path)))
