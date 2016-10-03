(ns lepiane.lang-test
  (:require  [cljs.test :refer-macros [is are deftest testing use-fixtures]]
             [goog.dom :as dom]

             [lepiane.data.lang :as lang]))

(defn append
  "Append all children to parent."
  [parent & children]
  (do (doseq [child children]
        (dom/appendChild parent child))
      parent))

(defn body
  []
  (-> js/document
      (.getElementsByTagName "body")
      (aget 0)))

(defn add-lang-script
  [lang]
  (let [script (dom/htmlToDocumentFragment
                (str "<script id='__lang-detect' type='text/lang-detect'>"
                     lang
                     "</script>"))
        b (body)]
    (dom/removeChildren body)
    (append b script)
    script))

(deftest fetching-body
  (is (not (nil? (body)))))

(deftest can-append-script-tag
  (is (not (nil? (add-lang-script "it")))))

(deftest can-detect-language
  #_(add-lang-script "it")
  (is (= "it" (lang/detect-language))))
