#!/usr/bin/env ruby

# Implementation of Quick-union (lazy approach) from the Algorithms course from
# Princeton University on Coursera
# initialisation - N
# union - N
# find  - N
# Takes N^2 array accesses to process sequence of N union commands on N objects

class QuickUnionUF
  attr_reader :id # for rspec

  def initialize(num_elements)
    @id = Array.new(num_elements){|i|i}
  end

  def union(p, q)
    unless connected(p, q)
      @id[root(p)] = root(q)
    end
  end

  def connected(p, q)
    root(p) == root(q)
  end

  def find(p)
    @id.index(p)
  end

  def count
    @id.count
  end

  def root(i)
    while i != @id[i]
      i = @id[i]
    end
    return i
  end

  def print_id
    @id.each_with_index {|item, index| print " #{index} "}
    print "\n#{@id}\n"
  end

  private :root

end

