#((fn [[f & rs]]
    (if-not f []
      (reverse 
        (reduce 
          (fn [[[ff fs] & rs :as r] e] 
            (if (= (dec e) fs) (cons [ff e] rs) (cons [e e] r)))
          [[f f]] rs))))
  (sort (set %)))
