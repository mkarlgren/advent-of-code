(require '[clojure.java.io :as io])

(defn reader [file] (io/reader (str (.getParent (io/file *file*)) "/" file)))


(defn fully-contains [sections]
  (some
   (comp #(apply <= %) (partial map #(nth sections %)))
   [[0 2 3 1] [2 0 1 3]]))

(with-open
 [r (reader "04_input.txt")]
  (->>
   (line-seq r)
   (filter
    (fn [pair-assignments]
      (->>
       (re-seq #"\d+" pair-assignments)
       (map #(Long/parseLong %))
       (fully-contains))))
   (count)))
