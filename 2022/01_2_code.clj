(require '[clojure.java.io :as io])

(defn reader [file] (io/reader (str (.getParent (io/file *file*)) "/" file)))


(with-open
 [r (reader "01_input.txt")]
  (->>
   (line-seq r)
   (reduce
    (fn [elves-cals cal-string]
      (if (= "" cal-string)
        (conj elves-cals 0)
        (cons
         (+ (first elves-cals) (Long/parseLong cal-string))
         (rest elves-cals))))
    '(0))
   (sort >)
   (take 3)
   (apply +)))
