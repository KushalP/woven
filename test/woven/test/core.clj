(ns woven.test.core
  (:use woven.core (textile))
  (:use [clojure.test]))

(deftest heading-1
  (is (= "<h1>Heading 1</h1>" (textile "h1. Heading 1"))))

(deftest heading-2
  (is (= "<h2>Heading 2</h2>" (textile "h2. Heading 2"))))

(deftest heading-3
  (is (= "<h3>Heading 3</h3>" (textile "h3. Heading 3"))))

(deftest heading-4
  (is (= "<h4>Heading 4</h4>" (textile "h4. Heading 4"))))

(deftest heading-5
  (is (= "<h5>Heading 5</h5>" (textile "h5. Heading 5"))))

(deftest heading-6
  (is (= "<h6>Heading 6</h6>" (textile "h6. Heading 6"))))

(deftest heading-multiline
  (is (=
       "<h1>Heading 1</h1>\n<h2>Heading 2</h2>\n<h3>Heading 3</h3>"
       (textile
        "h1. Heading 1\nh2. Heading 2\nh3. Heading 3"))))
