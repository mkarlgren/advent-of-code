(require '[clojure.java.io :as io])

(defn reader [file] (io/reader (str (.getParent (io/file *file*)) "/" file)))


(with-open
 [r (reader "06_input.txt")]
  (->>
   (line-seq r)
   (doall)
   (map (fn [line-chars]
    (loop [i 0]
      (let [distinct-count 4
            current-chars (seq (subs line-chars i (+ i distinct-count)))]
        (if (every?
             #(< (count (filter (partial = %) current-chars)) 2)
             current-chars)
          (+ i distinct-count)
          (recur (inc i)))))))))
