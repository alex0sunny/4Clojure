(fn fre [no-brackets nested-brackets in]
  (#(if (= in %) (if (re-seq no-brackets in) true false)
        (fre no-brackets nested-brackets %)) 
    (clojure.string/replace in nested-brackets "")))
#"^[^(){}\[\]]*$"
#"\([^(){}\[\]]*\)|\{[^(){}\[\]]*\}|\[[^(){}\[\]]*\]"
