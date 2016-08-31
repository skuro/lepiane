(ns lepiane.data.lang)

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
