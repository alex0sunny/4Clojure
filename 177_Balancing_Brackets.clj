(fn fre [nested-brackets in]
  (#(if (= in %) (nil? (re-find #"[(){}\[\]]" in))
        (fre nested-brackets %))
    (clojure.string/replace in nested-brackets "")))
#"\([^(){}\[\]]*\)|\{[^(){}\[\]]*\}|\[[^(){}\[\]]*\]"
