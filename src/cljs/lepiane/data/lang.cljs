(ns lepiane.data.lang
  (:require [lepiane.state :as state]
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

(defn translatable?
  [lang]
  ((set (keys strings)) (keyword lang)))

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

(defn get-language
  [state]
  (get-in @state [::lang]))

(defn set-language!
  [state lang]
  (swap! state assoc ::lang lang))

(set-language! state/state (lang-map (detect-language)))

(defn switch-language
  [lang]
  (if (translatable? lang)
    (let [l (langs lang :en)]
      (set-language! state/state (lang-map l))
      (get-language state/state))
    (do
      (js/alert "Language not yet supported, come back later!"))))

(defn event->language [ev]
  (let [select-box (.. ev -target)
        selected (aget (.-selectedOptions select-box) 0)]
    (when selected
      (switch-language (.. selected -id)))))

(defn lang-switcher [selected-lang]
  [:select {:id "lang"
            :value selected-lang
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
    (let [strings (:strings (get-language state/state))]
      (get-in strings path))))
