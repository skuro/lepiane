(ns lepiane.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [lepiane.core-test]))

(doo-tests 'lepiane.core-test
           'lepiane.lang-test)
