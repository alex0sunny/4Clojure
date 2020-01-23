(fn f [n]
  (if (= n 0) #{""}
      (let [q (map f (range 1 n))
            t (map #(str "(" % ")") (f (dec n)))]
        (reduce conj (set t)
                     (for [[s p] (map list q (reverse q)) 
                           a s b p]
                       (str a b))))))
