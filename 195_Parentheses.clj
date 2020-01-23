(fn [n]
  (if (= n 0) #{""}
    (#(reduce conj (set %)
                   (for [[ps1 ps2] (map list %2 (reverse %2))
                         str1 ps1 str2 ps2]
                     (str str1 str2)))
      (map #(str "(" % ")") (fpar (dec n)))
      (map fpar (range 1 n)))))
