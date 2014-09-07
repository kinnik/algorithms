#!/usr/bin/env ruby

# Implementation of Quick-find (eager approach) from the Algorithms course from
# Princeton University on Coursera
# initialisation - N
# union - N
# find  - 1
# Takes N^2 array accesses to process sequence of N union commands on N objects

class QuickFindUF
  attr_reader :id # for rspec

  def initialize(num_elements)
    @id = Array.new(num_elements){|i|i}
  end

  def union(p, q)
      id_p = @id[p]
      @id.map! {|x| x == id_p ? @id[q] : x}
  end

  def connected(p, q)
    @id[p] == @id[q]
  end

  def find(p)
    @id.index(p)
  end

  def count
    @id.count
  end

  def print_id
    @id.each_with_index {|item, index| print " #{index} "}
    print "\n#{@id}\n"
  end

end

