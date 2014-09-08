#!/usr/bin/env ruby

# Implementation of Weighted Quick-union (lazy approach) from the Algorithms course from
# Princeton University on Coursera
# Initialisation: N
# Find: log_2 N
# Union: log_2 N (includes cost of finding roots)

class WeightedQuickUnionUF
  attr_reader :id     # for rspec
  attr_reader :count  # for rspec
  attr_reader :tree_size # for rspec

  def initialize(num_elements)
    @id = Array.new(num_elements){|i|i}
    @tree_size = Array.new(num_elements){|i|1}
    @count = num_elements
  end

  # link root of smaller tree to root of larger tree
  # update the tree_size array of the root
  def union(p, q)
    root_p = find(p)
    root_q = find(q)

    unless root_p == root_q
      if @tree_size[root_p] < @tree_size[root_q]
        @id[root_p] = root_q
        @tree_size[root_q] += @tree_size[root_p]
      else
        @id[root_q] = root_p
        @tree_size[root_p] += @tree_size[root_q]
      end
      @count -= 1
    end
  end

  def connected(p, q)
    find(p) == find(q)
  end

  def find(i)
    while i != @id[i]
      @id[i] = @id[@id[i]] ## path compression
      i = @id[i]
    end
    return i
  end

  def print_id
    @id.each_with_index {|item, index| print " #{index} "}
    print "\n#{@id}\n"
  end

end

