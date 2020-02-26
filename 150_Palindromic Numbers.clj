(fn fnx [n] 
  (let [fd (fn [x] (map #(- (int %) 48) (str x)))
        fm #(Long. (apply str %))
        ds (fd n)
        l (count ds)
        lh (quot (dec l) 2)
        m (nth ds lh)
        dh (take lh ds)
        fi #(cons % (if (even? l) [%]))
        nn (fm (concat dh (fi m) (reverse dh)))]
    (#(if (< nn n) (fnx %) (cons nn (lazy-seq (fnx %))))
      (cond       
        (apply = 9 (fd nn)) (+ nn 2)
        (= 9 m)
          (#(fm (concat % (fi 0) (reverse %)))
            (fd (inc (fm dh))))
        :else (fm (concat dh (map inc (fi m)) (reverse dh)))))))
