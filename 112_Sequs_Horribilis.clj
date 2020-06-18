#(or (:s (% %2 %3)) [])
(fn fs [n [f & r :as s]]
  (if f 
      (if (sequential? f) 
          (let [{nn :n f-new :s} (fs n f)]
            (if f-new
                {:n nn 
                 :s (cons f-new 
                          (if (= f f-new) (:s (fs nn r)))) } ))
          (let [nn (- n f)]
              { :n nn 
		:s (if (>= nn 0) (cons f (:s (fs nn r)))) } ))))
