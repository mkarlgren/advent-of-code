(require '[clojure.java.io :as io])

(defn reader [file] (io/reader (str (.getParent (io/file *file*)) "/" file)))


(with-open
 [r (reader "05_input.txt")]
  (->>
   (line-seq r)
   (reduce
    (fn [stacks line]
      (cond
        (re-find #"\[" line)
        (->>
         (re-seq #"....?" line)
         (reduce
          (fn [stacks-index s]
            (let
             [[stacks i] stacks-index
              crate (second s)]
              [(update
                stacks i
                #(or
                  (and (= \space crate) (or % '()))
                  (concat % [crate])))
               (inc i)]))
          [stacks 1])
         (first))

        (re-find #"move" line)
        (->>
         (re-seq #"\d+" line)
         (map #(Long/parseLong %))
         ((fn [move-spec]
            (let
             [[amount from to] move-spec
              [crates stack] (split-at amount (nth stacks from))]
              (update
               (assoc stacks from stack)
               to #(concat crates %))))))

        :else stacks))
    ['()])
   (map first)
   (apply str)))
