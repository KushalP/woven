(ns woven.core
  (:require [clojure.string :as str]))

(def heading-regex #"h(\d)(\S*)\.\s*(.*)")

(defn heading-block [inner size]
  ^{:doc "Given some inner text to bound and a heading size,
          produce the HTML block for a heading"}
  (str "<h" size ">" inner "</h" size ">"))

(defn heading-parse [text]
  ^{:doc "Given a string, parses a heading using our regex,
          otherwise returns the text"}
  (let [parsed (re-find heading-regex text)]
    (if (nil? parsed)
      text
      (let [[original size _ inner] parsed]
        (heading-block inner size)))))

(defn split-and-trim-lines [text]
  ^{:doc "Given a string, split on newlines and trim each line"}
  (vec (map (fn [line] (str/trim line))
            (str/split-lines text))))

(defn textile-parser [lines]
  ;; Join the lines when we're reading to return.
  (str/join "\n" (map (fn [line] (heading-parse line)) lines)))

(defn textile [text]
  ;; Parse each line through each of our regexes after cleaning input.
  (textile-parser (split-and-trim-lines text)))
