#(nth 
  (iterate 
    (fn [[_ & r :as s]] 
      (concat [1] (map + r s) [1])) 
    [1]) 
  (dec %))
