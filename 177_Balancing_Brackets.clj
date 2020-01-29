(fn fre [no-brackets nested-brackets in]
  (#(if (= in %) (not= nil (re-seq no-brackets in))
        (fre no-brackets nested-brackets %)) 
    (clojure.string/replace in nested-brackets "")))
#"^[^(){}\[\]]*$"
#"\([^(){}\[\]]*\)|\{[^(){}\[\]]*\}|\[[^(){}\[\]]*\]"
