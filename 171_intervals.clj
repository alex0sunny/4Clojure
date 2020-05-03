#(if (empty? %) []
	   (apply reduce 
              (fn [[[f s] & rs :as r] e]
                (if (= e (dec f)) (cons [e s] rs) (cons [e e] r)))
              ((fn [[f & rs]] [[[f f]] rs]) (sort > (set %)))))
