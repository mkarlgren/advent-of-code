(require 'clojure.java.io)

(defn reader [file] (clojure.java.io/reader (str (.getParent (clojure.java.io/file *file*)) "/" file)))


(with-open
 [r (reader "02_input.txt")]
  (->>
   (line-seq r)
   (reduce
    (fn [x y]
      (+
       x
       ({(keyword "A X") (+ 1 3)
         (keyword "A Y") (+ 2 6)
         (keyword "A Z") (+ 3 0)
         (keyword "B X") (+ 1 0)
         (keyword "B Y") (+ 2 3)
         (keyword "B Z") (+ 3 6)
         (keyword "C X") (+ 1 6)
         (keyword "C Y") (+ 2 0)
         (keyword "C Z") (+ 3 3)}
        (keyword y))))
    0)))
