(fn fre [in]
  (#(if (= in %) (nil? (re-find #"[(){}\[\]]" in)) (fre %))
    (clojure.string/replace 
      in 
      #"\([^(){}\[\]]*\)|\{[^(){}\[\]]*\}|\[[^(){}\[\]]*\]" 
      "")))
