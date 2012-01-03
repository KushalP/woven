(ns woven.test.core
  (:use [woven.core :only
         [textile heading-block heading-parse]])
  (:use [clojure.test]))

(deftest headings
  (is (= "<h1>Heading 1</h1>" (textile "h1. Heading 1")))
  (is (= "<h2>Heading 2</h2>" (textile "h2. Heading 2")))
  (is (= "<h3>Heading 3</h3>" (textile "h3. Heading 3")))
  (is (= "<h4>Heading 4</h4>" (textile "h4. Heading 4")))
  (is (= "<h5>Heading 5</h5>" (textile "h5. Heading 5")))
  (is (= "<h6>Heading 6</h6>" (textile "h6. Heading 6"))))

(deftest heading-multiline
  (is (=
       "<h1>Heading 1</h1>\n<h2>Heading 2</h2>\n<h3>Heading 3</h3>"
       (textile
        "h1. Heading 1\nh2. Heading 2\nh3. Heading 3"))))

(deftest heading-block-test
  (is (= "<h1>bleh</h1>" (heading-block "bleh" 1)))
  (is (= "<h3>test</h3>" (heading-block "test" 3))))

(deftest heading-parse-test
  (is (= "test" (heading-parse "test")))
  (is (= "<h1>test</h1>" (heading-parse "h1. test"))))
