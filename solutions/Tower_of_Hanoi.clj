(fn move-tower 
  ([[towers :as towers-list] src trg n]
    (if (= n 1)
        (let [[ring] (towers src)
              src-tower (rest (towers src))
              trg-tower (cons ring (towers trg))] 
          (cons (#(assoc % trg trg-tower) 
                  (assoc towers src src-tower))
                towers-list))
        (let [[interm] (remove #{src trg} (range 3))
              list1 (move-tower towers-list src interm (dec n))
              list2 (move-tower list1 src trg 1)]
          (move-tower list2 interm trg (dec n)))))
  ([n] (reverse (move-tower [[(range 1 (inc n)) [] []]] 0 2 n))))
