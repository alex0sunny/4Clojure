(fn [f & rs] 
  (let [[m n s t] rs 
        ft (fn ft [fu p] (lazy-seq (cons (fu p) (ft fu (inc p)))))
        sq (((fn [fs] (partial ft #(fs % (or n 0))))
                #(ft (partial f %) %2))
              (or m 0))]
    (if s (take s (map #(take t %) sq)) sq)))
