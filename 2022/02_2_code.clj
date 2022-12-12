(require '[clojure.java.io :as io])

(defn reader [file] (io/reader (str (.getParent (io/file *file*)) "/" file)))


(with-open
 [r (reader "02_input.txt")]
  (->>
   (line-seq r)
   (reduce
    (fn [score strategy]
      (+
       score
       ({(keyword "A X") (+ 3 0)
         (keyword "A Y") (+ 1 3)
         (keyword "A Z") (+ 2 6)
         (keyword "B X") (+ 1 0)
         (keyword "B Y") (+ 2 3)
         (keyword "B Z") (+ 3 6)
         (keyword "C X") (+ 2 0)
         (keyword "C Y") (+ 3 3)
         (keyword "C Z") (+ 1 6)}
        (keyword strategy))))
    0)))
