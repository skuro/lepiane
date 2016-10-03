(ns lepiane.data.lang
  (:require [reagent.ratom :refer [atom]]

            [lepiane.data.lang.en :as en]
            [lepiane.data.lang.it :as it]))

(def default-lang "en")

(def langs
  {"en" {:id :en
         :flag "🇬🇧"}
   "it" {:id :it
         :flag "🇮🇹"}
   "de" {:id :de
         :flag "🇩🇪"}
   "fr" {:id :fr
         :flag "🇫🇷"}
   "nl" {:id :nl
         :flag "🇳🇱"}})

(def strings
  {:en en/strings
   :it it/strings})

(defn script->lang
  [script]
  (.log js/console "script is:" (.-text script))
  (if script
    (get langs (.-text script) "en")
    default-lang))

(defn detect-language
  []
  (let [lang-script (.getElementById js/document "__lang-detect")]
    (script->lang lang-script)))

(defn lang-map [{:keys [id]}]
  {:lang id
   :strings (or (strings id)
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
  (.log js/console "selected lang is:" selected-lang)
  [:select {:id "lang"
            :defaultValue selected-lang
            :on-change event->language
            :style {:-moz-appearance "none"
                    :-webkit-appearance "none"
                    :-moz-border-radius "5px"
                    :-webkit-border-radius "5px"
                    :border-radius "5px"
                    :border "none"
                    :padding "0px 3px"}}
   (for [[label {:keys [id flag]}] langs]
     [:option {:id id
               :value id
               :key (gensym)} flag " " label])])

(defn string [path]
  (when path
    (let [strings (:strings @current)]
      (get-in strings path))))
