(ns woven.core
  (:require [clojure.string :as str])
  (:use [woven.util :only (split-and-trim-lines)]))

(def heading-regex #"h(\d)(\S*)\.\s*(.*)")
(def blockquote-regex #"^bq\. (.*)")

(defn blockquote-block [inner]
  ^{:doc "Given some inner text to bound, wrap it with
          some <blockquote> tags"}
  (str "<blockquote>" inner "</blockquote>"))

(defn blockquote-parse [text]
  ^{:doc "Given some textile text, parses any text that
          needs to be emphasised"}
  (let [parsed (re-find blockquote-regex text)]
    (if (nil? parsed)
      text
      (let [[original inner] parsed]
        (blockquote-block inner)))))

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

(defn textile-parser [lines]
  ;; Join the lines when we're reading to return.
  (str/join "\n" (map (fn [line] (blockquote-parse
                                 (heading-parse line))) lines)))

(defn textile [text]
  ;; Parse each line through each of our regexes after cleaning input.
  (textile-parser (split-and-trim-lines text)))
