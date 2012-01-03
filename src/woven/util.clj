(ns woven.util
  (:require [clojure.string :as str]))

(defn split-and-trim-lines [text]
  ^{:doc "Given a string, split on newlines and trim each line"}
  (vec (map (fn [line] (str/trim line))
            (str/split-lines text))))
